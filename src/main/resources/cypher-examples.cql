// Zwróć wszystkie node'y z bazy
MATCH(n)
RETURN n

// Utwórz Node User
CREATE(u:User {name: 'Jacek'}),
      (x:User {name: 'Marian'}),
      (z:User {name: 'Henryk'})

// Utwórz node Company i powiąż go relacją 'HIRES' z nodem User, którego imię = 'Jacek'
MATCH(u:User {name: 'Jacek'})
CREATE(c:Company {name: 'Britenet', address: 'Morwowa 1'})-[r:HIRES]->(u)

// Zwróć jakiekolwiek nody, kóre mają jakąkolwiek relację z innymi nodami
MATCH(n)-[r]-(x)
RETURN n, r, x

// Znajdź path przypisany do zmiennej 'p' gdzie nody zawierają określone propertasy i relacje i zwróć określony propertas noda
MATCH p = (u:User)-[r:OWNS]->(c:Car)
RETURN c.brand

// Dodaj/zmień propertas wskazanego noda
MATCH (c:Car {brand: 'Mercedes'})
SET c.model = 'W124', c.year = 1988

// Dodaj/zmień propertas wskazanej relacji
MATCH (c:Company {name: 'Britenet'})-[r:HIRES]-()
SET r.since = 2021

// Usuń wskazany propertas z określonego noda
MATCH (c:Car)
  WHERE c.model STARTS WITH 'W'
SET c.year = 1990

// USUŃ WSZYSTKIE NODY I RELACJE
MATCH(n)
DETACH DELETE n;


// Properties map
CREATE(a:Car {brand: 'Volvo', `properties.year`: 2022, `properties.vin`: 'VXXZ12345679'}),
      (b:Car {brand: 'Subaru', `properties.year`: 2018, `properties.vin`: 'CVN987256456'}),
      (c:Car {brand: 'Porsche', `properties.year`: 1995, `properties.vin`: 'UZD987456231'}),
      (u:Worker {name: 'Jacek', `properties.age`: 67, `properties.hasDrivingLicense`: true}),
      (x:Worker {name: 'Marian', `properties.age`: 18, `properties.hasDrivingLicense`: false}),
      (z:Worker {name: 'Henryk', `properties.age`: 45, `properties.hasDrivingLicense`: true}),
      (u)-[:OWNS]->(a),
      (x)-[:OWNS]->(b),
      (z)-[:OWNS]->(c)