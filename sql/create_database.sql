CREATE SCHEMA IF NOT EXISTS `music`;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `music`.*
TO `music_user`@`localhost`;