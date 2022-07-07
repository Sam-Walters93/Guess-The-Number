DROP DATABASE IF EXISTS guessgamedb;
CREATE DATABASE guessgamedb;

USE guessgamedb;

CREATE TABLE game(
    gameId INT PRIMARY KEY AUTO_INCREMENT,
    answer VARCHAR(4) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT 1 
);

CREATE TABLE round(

    roundId INT PRIMARY KEY AUTO_INCREMENT,
    gameId INT NOT NULL,
    FOREIGN KEY fk_Game_Id (gameId)
		REFERENCES game(gameId),
    guess VARCHAR(4) NOT NULL,
    `time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Result VARCHAR(50) NOT NULL
);

private static DataSource getDataSource() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("GuessGameDB");
        ds.setUser("root");
        ds.setPassword("S11381105v$");
        ds.setServerTimezone("America/Chicago");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);

        return ds;
}
