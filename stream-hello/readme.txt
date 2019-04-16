Spring Cloud Stream 使用延迟消息实现定时任务（RabbitMQ）需要rabbitMQ安装rabbitmq_delayed_message_exchange插件
在http://www.rabbitmq.com/community-plugins.html找到对应版本的插件即可，而且对rabbit MQ版本有最低要求(rabbitMQ 3.6.x及以上)

将下载好，解压得到的.ez文件copy到RabbitMQ安装目录下的plugins文件夹
然后在RabbitMQ Commands 路径(cmd)下执行rabbitmq-plugins enable rabbitmq_delayed_message_exchange命令启用该插件，
