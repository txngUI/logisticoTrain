CREATE TABLE voie (
    num_voie INT(11) NOT NULL,
    interdite TINYINT(1) NOT NULL,
    PRIMARY KEY (num_voie)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE rames (
    num_serie VARCHAR(12) NOT NULL,
    type_rame VARCHAR(50) NOT NULL,
    voie INT(11) DEFAULT NULL UNIQUE,
    conducteur_entrant VARCHAR(50) NOT NULL,
    PRIMARY KEY (num_serie),
    FOREIGN KEY (voie) REFERENCES voie(num_voie)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE taches (
    num_serie VARCHAR(12) NOT NULL,
    num_tache INT(11) NOT NULL,
    tache TEXT NOT NULL,
    PRIMARY KEY (num_serie, num_tache),
    FOREIGN KEY (num_serie) REFERENCES rames(num_serie)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;