CREATE TABLE IF NOT EXISTS maison (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numRue VARCHAR(255),
    nomProprio VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS personne (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    genre VARCHAR(255)
    maison_id INT,
    FOREIGN KEY (maison_id) REFERENCES MaisonOld(id)
);