SET search_path  TO JardinCollectif;

INSERT INTO membres(nomembre, prenom, nom, motdepasse, admin) VALUES
        (1, 'etienne', 'dagenais', '1234', TRUE),
        (2, 'charles', 'belanger', '2345', true),
        (3, 'jean', 'paul', '3456', true),
        (4, 'alva', 'gingras', '4567', true),
        (5, 'yvan', 'Desplantes', '5678', true);

INSERT INTO lots(nomlots, nbmaxmembre) VALUES
        ('Lot1', 10),
        ('Lot2', 10),
        ('Lot3', 10),
        ('Lot4', 10);

INSERT INTO assignation(nomembre, nomlots) VALUES
        (1, 'Lot1'),
        (2, 'Lot1'),
        (3, 'Lot3'),
        (2, 'Lot2'),
        (2, 'Lot3'),
        (4, 'Lot4'),
        (5, 'Lot4');

INSERT INTO plante(nomplante, tempsculture) VALUES
        ('marguerite', 30),
        ('pissenlit', 22),
        ('plantain commun', 43),
        ('orchide', 67),
        ('tulipes', 24);

INSERT INTO plants(nomlots, nomplante, dateplantaison, nbplants, nomembre) VALUES
        ('Lot1', 'marguerite', '2020-10-20', 3, 1),
        ('Lot2', 'pissenlit', '2020-10-20', 7, 2),
        ('Lot3', 'plantain commun', '2020-10-20', 12, 3),
        ('Lot4', 'orchide', '2020-10-20', 12, 4),
        ('Lot4', 'tulipes', '2020-10-20', 13, 5);

DELETE FROM plants WHERE nomlots = 'Lot1';

SELECT p1.nomplante, p1.tempsculture, COALESCE(p.nbplants, 0) AS NombreDePlants
FROM plante p1 LEFT JOIN plants p on p1.nomplante = p.nomplante;


SELECT l1.nomlots, p.nomplante, p.dateplantaison, (p.dateplantaison + pe.tempsculture) AS daterecolte
FROM lots l1 LEFT JOIN plants p ON l1.nomlots = p.nomlots
    LEFT JOIN plante pe ON p.nomplante = pe.nomplante
WHERE l1.nomlots = 'Lot4';


SELECT noMembre, prenom, nom FROM membres;

SELECT min(sub.nb)
FROM (SELECT count(a.nomlots) AS nb
FROM assignation a
WHERE a.nomlots IN (SELECT nomlots FROM assignation WHERE nomembre = 1)
GROUP BY a.nomlots) sub;

UPDATE membres SET admin = FALSE WHERE nomembre = 1;

SELECT nomlots FROM plants WHERE nomlots = ?;
SELECT * FROM plante;

SELECT * FROM lots;

SELECT * FROM assignation;

SELECT assign.nomlots, COALESCE(m1.nomembre, ' '), COALESCE(m1.prenom, ' '), COALESCE(m1.nom, ' ')
FROM jardincollectif.assignation assign LEFT JOIN jardincollectif.membres m1 ON assign.nomembre = m1.nomembre
ORDER BY assign.nomlots;


DELETE FROM demandes WHERE nomlots = 'Lot1';


SELECT count(nomlots) FROM assignation WHERE nomlots = 'Lot3';

SELECT nbmaxmembre FROM lots WHERE nomlots = 'Lot3';


SELECT lots.nomlots, a.nomembre, m1.prenom, m1.nom FROM lots LEFT JOIN assignation a on lots.nomlots = a.nomlots LEFT JOIN jardincollectif.membres m1 ON a.nomembre = m1.nomembre;

SELECT ((datePlantaison- CURRENT_DATE)>= p.tempsCulture) FROM JardinCollectif.plants pl JOIN JardinCollectif.plante p on pl.nomPlante = p.nomPlante where nomLots ='Lot2' AND pl.nomPlante ='pissenlit' AND noMembre= 2;
