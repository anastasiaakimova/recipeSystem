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