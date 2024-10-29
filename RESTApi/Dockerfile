FROM python:3.11

WORKDIR /app

COPY requirements.txt /app/
COPY config.py /app/

RUN pip install --no-cache-dir -r requirements.txt

COPY . /app/

EXPOSE 5001

ENTRYPOINT [ "./start-server.sh" ]
