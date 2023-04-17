## Process.bpmn
![process](https://i.ibb.co/MRdGg7X/process.png)

## Настройки приложения
Для запуска необходимо установить elasticsearch
```
docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
docker start es762
```

Домены почт подгружаеются из файла mails.txt при старте приложения.  
Добавить домен вручную:
```
http://localhost:8080/email?email=test.com
```

## Запуск бизнес-процесса

Отправляем запрос на регистрацию
![request](https://i.ibb.co/Npw4LXc/reqreg.png)

Если домен не прошел автоматическую проверку, открывается userTask  
camunda login: a password: a  
http://localhost:8080/camunda/app/tasklist/default/

Подтверждаем почту 
![request](https://i.ibb.co/Jp9SNTh/verif-Mail.png)

Далее на почту приходит оповещение о успешной регистрации
![request](https://i.ibb.co/ygWKdL1/emailmsg.png)

