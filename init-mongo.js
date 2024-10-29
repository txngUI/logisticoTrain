print("Started Adding the User.");

db = db.getSiblingDB("history-db");
db.createUser({
  user: "anAdmin",
  pwd: "1a2z3e4R",
  roles: [{ role: "readWrite", db: "history-db" }],
});

print("End Adding the User Role.");