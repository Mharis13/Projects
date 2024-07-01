from fastapi import APIRouter
from sqlalchemy import select
from config.db import conn
from models.classes import classes
from schemas.class_schema import ClassModel
from typing import List

class_lol = APIRouter()


@class_lol.get('/classes', response_model=List[ClassModel])
def get_classes():
    return conn.execute(select(classes)).fetchall()





