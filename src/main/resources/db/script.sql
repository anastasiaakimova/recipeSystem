CREATE TABLE recipe
(
    id serial NOT NULL,
    name character varying,
    description character varying,
    PRIMARY KEY (id)
);

CREATE TABLE ingredient
(
    id serial NOT NULL,
    name character varying,
    calories real,
    PRIMARY KEY (id)
);

CREATE TABLE recipe_ingredient
(
    id serial NOT NULL,
    "idRecipe" integer,
    "idIngredient" integer,
    "requiredAmount" integer,
    PRIMARY KEY (id),
    CONSTRAINT "idRecipe" FOREIGN KEY ("idRecipe")
        REFERENCES recipe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "idIngredient" FOREIGN KEY ("idIngredient")
        REFERENCES ingredient (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE recipe ADD CONSTRAINT name_unique UNIQUE (name);

CREATE UNIQUE INDEX ingredient_unique_name ON ingredient (LOWER(name));

CREATE UNIQUE INDEX recipe_unique_name ON recipe (LOWER(name));

INSERT INTO recipe VALUES ('1', 'драники', 'Традиционное блюдо');
INSERT INTO recipe VALUES ('2', 'блины', 'Очень вкусно');
INSERT INTO recipe VALUES ('3','пицца', 'очень круто');


INSERT INTO ingredient VALUES ('1','Картофель', '200');
INSERT INTO ingredient VALUES ('2','Лук', '50');
INSERT INTO ingredient VALUES ('3','Пеперони', '300');

INSERT INTO recipe_ingredient VALUES ('1','1','1', '500');
INSERT INTO recipe_ingredient VALUES ('2','1','2','30');

