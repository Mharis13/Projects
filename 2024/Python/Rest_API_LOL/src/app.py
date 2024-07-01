from fastapi import FastAPI
from routes.champion import champion
from routes.class_lol import class_lol
from routes.difficulty import router

app = FastAPI()
app.include_router(champion)
app.include_router(class_lol)
app.include_router(router)


