CREATE TYPE task_status AS ENUM
    ('PLANNING', 'IN_PROGRESS', 'TESTING', 'COMPLETED', 'CANCELLED');
CREATE TYPE task_priority AS ENUM
    ('LOW', 'MEDIUM', 'HIGH', 'URGENT');

CREATE TABLE tasks(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status task_status NOT NULL DEFAULT 'PLANNING',
    priority task_priority NOT NULL DEFAULT 'MEDIUM',
    estimated_hours DECIMAL(10,2) DEFAULT 0.0,
    actual_hours DECIMAL(10,2) DEFAULT 0.0,
    due_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    project_id BIGINT NOT NULL,
    assignee_id BIGINT,
    reporter_id BIGINT,
    CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_assignee_id FOREIGN KEY (assignee_id) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT fk_reporter_id FOREIGN KEY (reporter_id) REFERENCES users(id) ON DELETE RESTRICT
);