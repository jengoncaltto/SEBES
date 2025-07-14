-- Tabela: usuario
CREATE TABLE usuario (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nome_usuario VARCHAR(15) not null,
    email VARCHAR(255) NOT NULL,
    email_recuperacao VARCHAR(255),
    tipo VARCHAR(255) NOT NULL
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
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    periodo INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL
);

-- Tabela: processo_seletivo
CREATE TABLE processo_seletivo (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_encerramento TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    id_bolsa INT NOT NULL REFERENCES bolsa(id)
);

-- Tabela: etapa
CREATE TABLE etapa (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    tipo_etapa VARCHAR(255) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_encerramento TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    id_processo_seletivo VARCHAR(36) NOT NULL REFERENCES processo_seletivo(id)
);

-- Tabela: inscricao
CREATE TABLE resposta_formulario (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    data_envio TIMESTAMP,
    tipo_forms VARCHAR(255) NOT NULL,
    conteudo TEXT,
    status VARCHAR(255) NOT NULL,
    id_resposta_associada VARCHAR(36) NOT NULL REFERENCES resposta_formulario(id),
    id_usuario VARCHAR(36) NOT NULL REFERENCES usuario(id),
    id_processo_seletivo VARCHAR(36) NOT NULL REFERENCES processo_seletivo(id)
);

-- Tabela: publicacao
CREATE TABLE publicacao (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    data_publicacao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP,
    conteudo TEXT,
    id_servidor VARCHAR(36) NOT NULL REFERENCES servidor_prae(id)
);

CREATE TABLE login_codes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    code VARCHAR(6) NOT NULL,
    expiration TIMESTAMP NOT NULL,
    used BOOLEAN DEFAULT FALSE
);