from sqlalchemy import String, Table,Column
from sqlalchemy.sql.sqltypes import Integer
from config.db import meta, engine
classes = Table("classes",meta,
                  Column("class_id",Integer,primary_key=True),
                  Column("class_name",String(50)))

meta.create_all(engine)