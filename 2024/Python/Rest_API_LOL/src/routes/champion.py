from fastapi import APIRouter, HTTPException
from sqlalchemy.exc import IntegrityError
from sqlalchemy import select
from config.db import conn
from models.champion import champions
from models.difficulties import difficulties
from models.classes import classes
from schemas.champion_schema import ChampionModel, ChampionCreateModel
from typing import List

champion = APIRouter()

@champion.get('/champions', response_model=List[ChampionModel])
def get_champions():
    query = select(
        champions.c.id_champion,
        champions.c.champion_name,
        classes.c.class_name,
        difficulties.c.difficulty_name
    ).join(
        classes, champions.c.class_id_fk == classes.c.class_id
    ).join(
        difficulties, champions.c.difficulty_id_fk == difficulties.c.difficulty_id
    ).order_by(champions.c.id_champion)
    results = conn.execute(query).fetchall()
    # Convertir los resultados en modelos de respuesta
    return [ChampionModel(id=row[0], name=row[1], class_name=row[2], difficulty_name=row[3]) for row in results]


@champion.get('/{champion}', response_model=ChampionModel)
def get_champion(champion: str):
    query = select(
        champions.c.id_champion,
        champions.c.champion_name,
        classes.c.class_name,
        difficulties.c.difficulty_name
    ).join(
        classes, champions.c.class_id_fk == classes.c.class_id
    ).join(
        difficulties, champions.c.difficulty_id_fk == difficulties.c.difficulty_id
    ).where(
        champions.c.champion_name == champion
    )
    
    result = conn.execute(query).first()
    if result is None:
        raise HTTPException(status_code=404, detail="Champion not found.")
    
    return ChampionModel(id=result[0], name=result[1], class_name=result[2], difficulty_name=result[3])

@champion.get('/champion/{id_champion}', response_model=ChampionModel)
def get_champion_by_id(id_champion: int):
    query = select(
        champions.c.id_champion,
        champions.c.champion_name,
        classes.c.class_name,
        difficulties.c.difficulty_name
    ).join(
        classes, champions.c.class_id_fk == classes.c.class_id
    ).join(
        difficulties, champions.c.difficulty_id_fk == difficulties.c.difficulty_id
    ).where(
        champions.c.id_champion == id_champion
    )
        
    result = conn.execute(query).first()
    if result is None:
        raise HTTPException(status_code=404, detail="Champion not found.")
    
    return ChampionModel(id=result[0], name=result[1], class_name=result[2], difficulty_name=result[3])

@champion.post('/champions/', response_model=ChampionModel)
def create_champion(champion_data: ChampionCreateModel):
    # Asumiendo que ChampionCreateModel ahora tiene class_id y difficulty_id
    class_id = champion_data.class_id
    difficulty_id = champion_data.difficulty_id

    # Verificar si los IDs existen en las tablas correspondientes
    class_exists = conn.execute(select(classes.c).where(classes.c.class_id == class_id)).first()
    if not class_exists:
        raise HTTPException(status_code=400, detail=f"Class ID '{class_id}' not found.")

    difficulty_exists = conn.execute(select(difficulties.c).where(difficulties.c.difficulty_id == difficulty_id)).first()
    if not difficulty_exists:
        raise HTTPException(status_code=400, detail=f"Difficulty ID '{difficulty_id}' not found.")

    new_champion = {
        "champion_name": champion_data.name,
        "class_id_fk": class_id,
        "difficulty_id_fk": difficulty_id
    }

    try:
        result = conn.execute(champions.insert().values(new_champion))
        # Asegúrate de que el commit se ejecuta correctamente
        conn.commit()
        new_champion_id = result.inserted_primary_key[0]
        return get_champion_by_id(new_champion_id)
    except IntegrityError:
        conn.rollback()
        raise HTTPException(status_code=400, detail="Error adding champion")
    except Exception as e:
        conn.rollback()
        raise HTTPException(status_code=500, detail=str(e))
    
@champion.delete('/champions/{id_champion}', response_model=dict)
def delete_champion(id_champion: int):
    # Verificar si el campeón existe
    exists = conn.execute(select(champions.c.id_champion).where(champions.c.id_champion == id_champion)).first()
    if not exists:
        raise HTTPException(status_code=404, detail="Champion not found.")

    try:
        # Eliminar el campeón
        conn.execute(champions.delete().where(champions.c.id_champion == id_champion))
        conn.commit()
        return {"message": f"Champion with id {id_champion} has been deleted."}
    except IntegrityError:
        conn.rollback()
        raise HTTPException(status_code=400, detail="Cannot delete champion due to integrity constraints.")
    except Exception as e:
        conn.rollback()
        raise HTTPException(status_code=500, detail=str(e))@champion.delete('/champions', response_model=dict)
def delete_all_champions():
    try:
        # Eliminar todos los campeones
        conn.execute(champions.delete())
        conn.commit()
        return {"message": "All champions have been deleted."}
    except Exception as e:
        conn.rollback()
        raise HTTPException(status_code=500, detail=str(e))