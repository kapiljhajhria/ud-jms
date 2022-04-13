# Getting Started

## Using Docker for ActiveMQ-artemis
https://hub.docker.com/r/qoricode/activemq-artemis

```bash
docker run -it --rm \
  -p 8161:8161 \
  -p 61616:61616 \
  qoricode/activemq-artemis
```

Visit http://localhost:8161/ to see the ActiveMQ-artemis console.

check the web console using the default username and password of artemis / simetraehcapa.

To read documentation visit the project it was forked from [HERE](https://github.com/vromero/activemq-artemis-docker)