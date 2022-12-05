from pymongo import MongoClient 
import json 
from threading import Thread
import time
import send as send
import os
uri = "mongodb://%s:%s@%s/?authMechanism=DEFAULT&authSource=UniMarketDB" % (
                'uni', 'uni1234', 'db.yoonleeverse.com')
client=MongoClient(uri)
def monitor(user,title,Max,Min,Filter,region):
    db=client['UniMarketDB']
    collection=db['data']

    db=client['UniMarketDB']
    monitor_collection=db['monitor']

    data=[]
    current_path = os.getcwd()
    cred_path = current_path+"/server/item_id_list.txt"
    print(cred_path)
    f=open(cred_path,'r')

    while True:
        line=f.readline().rstrip()
        if not line:break
        data.append(line)
    for i in collection.find({'$and':[{'$and':[{"price":{"$lte":Max}},{"price":{"$gte":Min}},
    {"title":{"$regex":".*{}.*".format(title)}}]},{'$nor':[{"title":{"$regex":".*{}.*".format(Filter)}},
    {"region":{"$regex":".*{}.*".format(region)}}]}]}):#갱신된 부분이 있는지 확인
        if i['item_id'] not in data:
                send(user,i['title'])
                # 알림 보내기
                with open('/server/item_id_list.txt','a',encoding='UTF-8')as f:#알림 보내고 리스트 파일 갱신
                    f.write(i['item_id']+'\n')
    time.sleep(3)