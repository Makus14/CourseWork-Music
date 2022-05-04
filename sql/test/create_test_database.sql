CREATE SCHEMA IF NOT EXISTS `test_music`;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `test_music`.*
TO `music_user`@`localhost`;