INSERT INTO `skill` (`id`, `name`, `created_at`) VALUES
   (1, 'Java', NOW()),
   (2, 'C#', NOW()),
   (3, 'JavaScript', NOW());

INSERT INTO `developer` (`id`, `name`, `created_at`) VALUES
   (1, 'Kenneth Gottschalk de Azevedo', NOW()),
   (2, 'Caio Cesar Alves Borges', NOW());

INSERT INTO `developer_skill` (`developer_id`, `skill_id`, `created_at`) VALUES
   (1, 1, NOW()),
   (1, 2, NOW()),
   (2, 1, NOW()),
   (2, 3, NOW());
