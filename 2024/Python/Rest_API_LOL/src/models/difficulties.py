from sqlalchemy import String, Table,Column
from sqlalchemy.sql.sqltypes import Integer
from config.db import meta, engine

difficulties = Table("difficulties",meta,
                  Column("difficulty_id",Integer,primary_key=True),
                  Column("difficulty_name",String(50)),
                 )

meta.create_all(engine)