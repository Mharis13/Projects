from pydantic import BaseModel, Field

class ChampionModel(BaseModel):
    id: int
    name: str
    class_name: str = Field(..., alias='class')
    difficulty_name: str = Field(..., alias='difficulty')

    class Config:
        populate_by_name = True
        
class ChampionCreateModel(BaseModel):
    name: str
    class_id: int = Field(..., alias='class')
    difficulty_id: int = Field(..., alias='difficulty')
    class Config:
        populate_by_name = True
