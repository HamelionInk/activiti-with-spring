# activiti-with-spring

1. Запустить проект :)
2. Есть 4 Endpoint - 
                     /start?postValue='SomeValue' - В someValue написать значение типа int(Обработчик ошибки не стал добавлять, если вписать неверный тип, т.к в ТЗ этого                      нет)
                     Пример - http://localhost:8080/start?postValue=12
                     
                     /tasks - Возвращает все запущенные таски в формате JSON
                     
                     /complete_task/id - в id скопировать taskId из /tasks для завершения задачи и процесса. 
                     Пример - http://localhost:8080/complete_task/bd43a7a6-37b1-11ed-9083-94e23c7b2474
                     Если такая таска есть, то вернет значение task found - complete, если нету то task not found
                     
                     /result - выводит все завершенные задачи в форме JSON, возвращает id, taskId, taskDefinitionKey, postValue, randomData, sum
