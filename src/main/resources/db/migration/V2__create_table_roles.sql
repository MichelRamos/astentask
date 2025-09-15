CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR NOT NULL UNIQUE,
    description VARCHAR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);

INSERT INTO roles (name, description) VALUES 
    ('ADMIN', 'Acesso total ao sistema'),
    ('PROJECT_MANAGER', 'Pode criar projetos e gerenciar equipe'),
    ('DEVELOPER', 'Pode trabalhar em tarefas atribuídas'),
    ('VIEWER', 'Apenas visualização');