from sqlalchemy import String, Table, Column, ForeignKey
from sqlalchemy.orm import relationship
from sqlalchemy.sql.sqltypes import Integer
from config.db import meta, engine

champions = Table(
    "champions", meta,
    Column("id_champion", Integer, primary_key=True),
    Column("champion_name", String(50)),
    Column("class_id_fk", Integer, ForeignKey('classes.class_id')),
    Column("difficulty_id_fk", Integer, ForeignKey('difficulties.difficulty_id')) 
)

meta.create_all(engine)