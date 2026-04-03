CREATE TABLE equipamento (
                             id BIGSERIAL PRIMARY KEY,
                             nome VARCHAR(255) NOT NULL,
                             numero_serie VARCHAR(100) UNIQUE NOT NULL
);