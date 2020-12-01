CREATE SCHEMA IF NOT EXISTS  JardinCollectif;
SET search_path  TO JardinCollectif;


CREATE TABLE membres(
noMembre VARCHAR(80) NOT NULL,
prenom VARCHAR(80) NOT NULL,
nom VARCHAR(80) NOT NULL,
motDePasse VARCHAR(80) NOT NULL,
admin BOOLEAN NOT NULL,

CONSTRAINT membres_cc0 PRIMARY KEY (noMembre)
);

CREATE TABLE lots(
    nomLots VARCHAR(255) NOT NULL,
    nbMaxMembre INTEGER NOT NULL,

    CONSTRAINT lots_cc0 PRIMARY KEY (nomLots),
    CONSTRAINT lots_nbMaxMembre
                 CHECK(nbMaxMembre >= 1)
);

CREATE TABLE plante(
    nomPlante VARCHAR(255) NOT NULL,
    tempsCulture INTEGER NOT NULL,

    CONSTRAINT plante_cc0 PRIMARY KEY (nomPlante),
    CONSTRAINT plante_tempsCulture
                   CHECK(tempsCulture >= 1)
);

CREATE TABLE demandes(
    noMembre INTEGER NOT NULL,
    nomLots VARCHAR(255) NOT NULL,

    CONSTRAINT demandes_cc0
                     PRIMARY KEY(noMembre, nomLots),
    CONSTRAINT demandes_cr0
                     FOREIGN KEY (noMembre)
                     REFERENCES membres (noMembre),
    CONSTRAINT demandes_cr1
                     FOREIGN KEY (nomLots)
                     REFERENCES lots (nomLots)
);

CREATE TABLE assignation(
    noMembre INTEGER NOT NULL,
    nomLots VARCHAR(255) NOT NULL,

    CONSTRAINT assignation_cc0
                     PRIMARY KEY(noMembre, nomLots),
    CONSTRAINT assignation_cr0
                     FOREIGN KEY (noMembre)
                     REFERENCES membres (noMembre),
    CONSTRAINT assignation_cr1
                     FOREIGN KEY (nomLots)
                     REFERENCES lots (nomLots)
);

CREATE TABLE plants(
    nomLots VARCHAR(255) NOT NULL,
    nomPlante VARCHAR(255) NOT NULL,
    datePlantaison date NOT NULL,
    nbPlants INTEGER NOT NULL,

    CONSTRAINT plants_cc0 PRIMARY KEY (nomLots, nomPlante, datePlantaison),
    CONSTRAINT plants_cr0 FOREIGN KEY (nomLots) REFERENCES lots (nomLots),
    CONSTRAINT plants_cr1 FOREIGN KEY (nomPlante) REFERENCES plante (nomPlante),
    CONSTRAINT plants_nbPlants CHECK ( nbPlants >= 1 )
);
