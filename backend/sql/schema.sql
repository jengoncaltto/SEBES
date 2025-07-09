-- Tabela: usuario
CREATE TABLE usuario (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nome_usuario VARCHAR(15) not null,
    email VARCHAR(255) NOT NULL,
    email_recuperacao VARCHAR(255) NOT NULL
);

-- Tabela: discente
CREATE TABLE discente (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nome_social VARCHAR(255),
    matricula VARCHAR(11) NOT null,
    telefone VARCHAR(15) NOT null,
    id_usuario VARCHAR(36) NOT NULL REFERENCES usuario(id)
);

-- Tabela: servidor_prae
CREATE TABLE servidor_prae (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nome_social VARCHAR(255),
    setor VARCHAR(255) NOT NULL,
    cargo VARCHAR(255) NOT null,
    telefone VARCHAR(15) NOT null,
    id_usuario VARCHAR(36) NOT NULL REFERENCES usuario(id)
);

-- Tabela: bolsa
CREATE TABLE bolsa (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(500) not null,
    valor DECIMAL NOT null,
    periodo INT not NULL
);

-- Tabela: processo_seletivo
CREATE TABLE processo_seletivo (
    id VARCHAR(36) NOT NULL PRIMARY key,
    data_inicio TIMESTAMP NOT NULL,
    data_encerramento TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    id_bolsa INT NOT NULL REFERENCES bolsa(id)
);

-- Tabela: etapa
CREATE TABLE etapa (
    id VARCHAR(36) NOT NULL PRIMARY key,
    tipo_etapa VARCHAR(255) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_encerramento TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    id_processo_seletivo VARCHAR(36) NOT NULL REFERENCES processo_seletivo(id)
);


-- Tabela: inscricao
CREATE TABLE inscricao (
    id VARCHAR(36) NOT NULL PRIMARY key,
    data_envio TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    id_usuario VARCHAR(36) NOT NULL REFERENCES usuario(id),
    id_processo_seletivo VARCHAR(36) NOT NULL REFERENCES processo_seletivo(id)
);

-- Tabela: publicacao
CREATE TABLE publicacao (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    data_publicacao TIMESTAMP NOT NULL,
    conteudo VARCHAR(5000) NOT NULL,
    id_usuario VARCHAR(36) NOT NULL REFERENCES usuario(id)
);