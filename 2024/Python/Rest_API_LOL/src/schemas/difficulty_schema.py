from pydantic import BaseModel,Field

class DifficultyModel(BaseModel):
    difficulty_id: int = Field(...,alias='id')
    difficulty_name: str = Field(...,alias='name')
    
    class Config:
        populate_by_name = True