/*Role*/
INSERT INTO role (role_id, role) VALUES
  (1, 'STUDENT_ROLE'),
  (2, 'ADMIN_ROLE'),
  (3, 'LECTURER_ROLE')
ON CONFLICT (role_id) DO NOTHING;