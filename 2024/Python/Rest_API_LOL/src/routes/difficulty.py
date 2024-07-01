from fastapi import APIRouter
from sqlalchemy import select
from config.db import conn
from models.difficulties import difficulties  # Assuming this is your table model
from schemas.difficulty_schema import DifficultyModel
from typing import List

router = APIRouter()  

@router.get('/difficulties', response_model=List[DifficultyModel])
def get_difficulties():
    return conn.execute(select(difficulties)).fetchall()