FROM mysql:5.7.28

COPY data/visits.csv /var/lib/mysql-files/
COPY scripts/* docker-entrypoint-initdb.d/
