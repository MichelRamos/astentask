CREATE TABLE timelogs(
    id BIGSERIAL PRIMARY KEY,
    hours_worked INTERVAL,
    description TEXT,
    log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    task_id BIGINT NOT NULL,
    CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT
);