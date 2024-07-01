from pydantic import BaseModel, Field

class ClassModel(BaseModel):
    class_id: int = Field(..., alias='id')
    class_name: str = Field(...,alias ='name')
    
    class Config:
        populate_by_name = True