

INSERT INTO permissions (id, name) VALUES
            (1, 'READ_PRIVILEGES'),
            (2, 'WRITE_PRIVILEGES'),
            (3, 'DELETE_PRIVILEGES'),
            (4, 'MANAGE_USERS');


INSERT INTO role_permission (role_id, permission_id) VALUES
             (1, 1), -- ADMIN: READ_PRIVILEGES
            (1, 2), -- ADMIN: WRITE_PRIVILEGES
            (1, 3), -- ADMIN: DELETE_PRIVILEGES
             (1, 4); -- ADMIN: MANAGE_USERS


INSERT INTO role_permission (role_id, permission_id) VALUES
                (2, 1), -- USER: READ_PRIVILEGES
                (2, 2); -- USER: WRITE_PRIVILEGES


