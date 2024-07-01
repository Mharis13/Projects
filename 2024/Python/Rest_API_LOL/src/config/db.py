from sqlalchemy import create_engine, MetaData
from dotenv import load_dotenv
import os

load_dotenv()

db_password = os.getenv("DB_PASSWORD")

engine = create_engine(f"mysql+pymysql://root:{db_password}@localhost:3306/leagueoflegendsapi")
meta = MetaData()
conn = engine.connect()