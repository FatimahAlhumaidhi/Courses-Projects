DROP DATABASE IF EXISTS `BANK`;
CREATE DATABASE `BANK`;
USE `BANK`;

CREATE TABLE `EMPLOYEE` (
    `NID` CHAR(10) NOT NULL,
    `NAME` VARCHAR(50) NOT NULL,
    `DOB` DATE,
    `SALARY` DECIMAL(10 , 2),
    `MOBILE` VARCHAR(13),
    `SEX` CHAR,
    `EMAIL` VARCHAR(50),
    `INSURANCE` VARCHAR(11),
    `JOB TITLE` VARCHAR(50),
    `ADDRESS` VARCHAR(50) NOT NULL,
    `BRANCH#` CHAR(4),
    `DEPARTMENT#` CHAR(4),
    PRIMARY KEY (`NID`)
);

CREATE TABLE `DEPENDANT` (
    `NAME` VARCHAR(50) NOT NULL,
    `Employee ID` CHAR(10) NOT NULL,
    `DOB` DATE,
    `Relationship` VARCHAR(20),
    `SEX` CHAR,
    PRIMARY KEY (`NAME` , `Employee ID`),
    FOREIGN KEY (`Employee ID`) REFERENCES `EMPLOYEE` (`NID`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `DEPARTMENT` (
    `DEPARTMENT#` CHAR(4) NOT NULL,
    `DEPARTMENT NAME` VARCHAR(50) NOT NULL,
    `Manager ID` CHAR(10),
    PRIMARY KEY (`DEPARTMENT#`),
    FOREIGN KEY (`Manager ID`) REFERENCES `EMPLOYEE` (`NID`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `CUSTOMER` (
    `NID` CHAR(10) NOT NULL,
    `NAME` VARCHAR(50) NOT NULL,
    `DOB` DATE,
    `EMAIL` VARCHAR(50),
    `MOBILE` VARCHAR(13),
    `ADDRESS` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`NID`)
);

CREATE TABLE `BRANCH` (
    `BRANCH#` CHAR(4) NOT NULL,
    `Manager ID` CHAR(10),
    `LOCATION` VARCHAR(50),
    PRIMARY KEY (`BRANCH#`),
    FOREIGN KEY (`Manager ID`) REFERENCES `EMPLOYEE` (`NID`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `ACCOUNT` (
    `IBAN` CHAR(24) NOT NULL,
    `Balance` DECIMAL NOT NULL,
    `Customer ID` CHAR(10) NOT NULL,
    `STATUS` VARCHAR(30) NOT NULL,
    `TYPE` VARCHAR(30) NOT NULL,
    `BRANCH#` CHAR(4),
    PRIMARY KEY (`IBAN`),
    FOREIGN KEY (`BRANCH#`) REFERENCES `BRANCH` (`BRANCH#`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`Customer ID`) REFERENCES `CUSTOMER` (`NID`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `LOAN` (
    `LOAN#` INT NOT NULL AUTO_INCREMENT,
    `BRANCH#` CHAR(4),
    `TYPE` VARCHAR(30),
    `Interest Rate` DECIMAL(3 , 2 ),
    `DATE` DATE NOT NULL,
    `Customer ID` CHAR(10) NOT NULL,
    `Amount` DECIMAL(10 , 2 ) NOT NULL,
    `Paid Amount` DECIMAL(10 , 2 ),
    PRIMARY KEY (`LOAN#`),
    FOREIGN KEY (`BRANCH#`) REFERENCES `BRANCH` (`BRANCH#`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`Customer ID`) REFERENCES `CUSTOMER` (`NID`) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `CARD` (
    `CARD#` CHAR(16) NOT NULL,
    `IBAN` CHAR(24) NOT NULL,
    `Expiration Date` DATE NOT NULL,
    `CVV` CHAR(3) NOT NULL,
    `TYPE` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`CARD#` , `IBAN`)
);

CREATE TABLE `TRANSACTION` (
    `Reference#` INT NOT NULL AUTO_INCREMENT,
    `TYPE` VARCHAR(30) NOT NULL,
    `TIME STAMP` TIMESTAMP NOT NULL,
    `Currency` CHAR(3) NOT NULL,
    `Amount` DECIMAL(10 , 2 ) NOT NULL,
    `BRANCH#` CHAR(4),
    `Sender IBAN` CHAR(24) NOT NULL,
    `CARD#` CHAR(16),
    `Receiver IBAN` CHAR(24),
    `COMMENTS` TEXT,
    PRIMARY KEY (`Reference#`),
    FOREIGN KEY (`BRANCH#`) REFERENCES `BRANCH` (`BRANCH#`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`Sender IBAN`) REFERENCES `ACCOUNT` (`IBAN`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`CARD#`) REFERENCES `CARD` (`CARD#`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `BRANCH DEPARTMENTS` (
    `DEPARTMENT#` CHAR(4) NOT NULL,
    `BRANCH#` CHAR(4) NOT NULL,
    PRIMARY KEY (`BRANCH#` , `DEPARTMENT#`),
    FOREIGN KEY (`DEPARTMENT#`) REFERENCES `DEPARTMENT` (`DEPARTMENT#`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`BRANCH#`) REFERENCES `BRANCH` (`BRANCH#`) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE `EMPLOYEE` ADD FOREIGN KEY(`DEPARTMENT#`) REFERENCES `DEPARTMENT`(`DEPARTMENT#`) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE `EMPLOYEE` ADD FOREIGN KEY(`BRANCH#`) REFERENCES `BRANCH`(`BRANCH#`) ON DELETE SET NULL ON UPDATE CASCADE;


 -- QUERIES
INSERT INTO `BANK`.`DEPARTMENT`( `DEPARTMENT#` , `DEPARTMENT NAME` ) VALUES 
(  '0001'  , 'HEADQUARTERS'        ),
(  '1121'  , 'Financial DEPARTMENT'), 
(  '1122'  , 'IT DEPARTMENT'       ), 
(  '1123'  , 'Retail Banking'      ),
(  '1124'  , 'Insurance DEPARTMENT'), 
(  '1125'  , 'Customer Service'    ),
(  '1126'  , 'Human Resources'     ); 

INSERT INTO `BANK`.`BRANCH`( `BRANCH#` , `LOCATION`) VALUES
( '1210' , 'AL SAHAFA - Riyadh'     ),
( '1220' , 'IRQAH - Riyadh'         ),
( '1230' , 'AR RABWAH - Riyadh'     ),
( '1240' , 'AS SUWAIDI - Riyadh'    ),
( '1310' , 'AL FAISALIYAH - DAMMAM' ),
( '1320' , 'AL MANAR - DAMMAM'      ),
( '1330' , 'AN NADA - DAMMAM'       ),
( '1410' , 'AL SAFA - JEDDAH'       ),
( '1420' , 'AL THAGAR - JEDDAH'     ),
( '1430' , 'AN NASEEM - JEDDAH'     );

 INSERT INTO `EMPLOYEE`( `NID` , `NAME` , `DOB` , `SALARY` , `MOBILE` , `SEX` , `EMAIL` , `INSURANCE` , `JOB TITLE`, `ADDRESS` , `BRANCH#`, `DEPARTMENT#`) VALUES 	
( '0055555432' , 'fatimah abdullah', '1995-09-23' , '100000.00','966523463342' ,'F', 'fati@gmail.com' , '81272881734' , 'CEO'                            , 'AL SAHAFA - RIYADH'  , NULL ,'0001'),
( '0022224444' , 'Mohammed khalid' , '1981-03-20' , '70000.00' ,'966559951555' ,'M', 'moh@gmail.com'  , '12327541121' , 'Branch Manager'                 , 'AL GHADIR - Riyadh ' ,'1210','0001'),
( '0033337777' , 'Abdullah badr'   , '1988-07-24' , '70000.00' ,'966534255123' ,'M', 'abd@gmail.com'  , '87654798675' , 'Branch Manager'                 , 'AL MURUJ - Riyadh'   ,'1220','0001'),
( '0055554444' , 'Norah turki'     , '1992-12-12' , '70000.00' ,'966533258532' ,'F', 'nor@gmail.com'  , '45653264734' , 'Branch Manager'                 , 'AL OLAYA - Riyadh'   ,'1230','0001'),
( '0033335555' , 'Maram ahmad'     , '1993-11-04' , '70000.00' ,'966521955544' ,'F', 'mar@gmail.com'  , '12345878653' , 'Branch Manager'                 , 'AL WAHAH - Riyadh'   ,'1240','0001'),
( '0066664444' , 'Mohanad fares'   , '1991-06-22' , '70000.00' ,'966559951555' ,'M', 'mohn@gmail.com' , '76535436354' , 'Branch Manager'                 , 'AL AZIZAIH - DAMMAM' ,'1310','0001'),
( '0033336666' , 'Marwa fahad'     , '1993-11-04' , '70000.00' ,'966521955544' ,'F', 'mar@gmail.com'  , '87254635975' , 'Branch Manager'                 , 'ASH SHULAH - DAMMAM' ,'1320','0001'),
( '0066668888' , 'Fahad abdullah'  , '1989-03-11' , '70000.00' ,'966534255532' ,'M', 'fah@gmail.com'  , '54534235646' , 'Branch Manager'                 , 'AN NADA - DAMMAM'    ,'1330','0001'),
( '0055552222' , 'Mohammed tariq'  , '1994-02-22' , '70000.00' ,'966653676555' ,'M', 'moh2@gmail.com' , '53542342531' , 'Branch Manager'                 , 'AL AZIZAIH - JEDDAH' ,'1410','0001'),
( '0099997777' , 'Sara shareef'    , '1993-10-04' , '70000.00' ,'966521865589' ,'F', 'sar1@gmail.com' , '15435323346' , 'Branch Manager'                 , 'AL FALAH - JEDDAH'   ,'1420','0001'),
( '0022226666' , 'Badr kareem'     , '1990-03-21' , '70000.00' ,'966565466532' ,'M', 'bad1@gmail.com' , '29726976143' , 'Branch Manager'                 , 'AL THAALBAH - JEDDAH','1430','0001'),
( '0066669999' , 'Turki khalaf'    , '1991-02-24' , '50000.00' ,'966577776454' ,'M', 'mohn@gmail.com' , '69381591854' , 'Financial Manager'              , 'AL OLAYA - Riyadh'   ,'1210','1121'),
( '0044443333' , 'Mishal ahmad'    , '1998-04-24' , '50000.00' ,'966756565798' ,'M', 'mis@gmail.com'  , '46139843833' , 'IT Manager'                     , 'AL AQIQ - Riyadh'    ,'1210','1122'),
( '0022227777' , 'Hamad abdullah'  , '1991-06-23' , '50000.00' ,'966597765649' ,'M', 'ham3@gmail.com' , '66432534432' , 'Retail Banking Manager'         , 'AL OLAYA - Riyadh'   ,'1210','1123'),
( '0066661111' , 'Talal muhammad'  , '1999-02-28' , '50000.00' ,'966598867699' ,'M', 'tal4@gmail.com' , '87986532765' , 'Insurance Manager'              , 'AL YASMIN - Riyadh'  ,'1210','1124'),
( '0066665555' , 'Yaser faisal'    , '1981-02-07' , '50000.00' ,'966535678999' ,'M', 'yas1@gmail.com' , '53678663644' , 'Customer Service Manager'       , 'AL MALQA - Riyadh'   ,'1210','1125'),
( '0066667777' , 'Hind adbulrahman', '1994-05-24' , '50000.00' ,'966556565854' ,'F', 'hind@gmail.com' , '762234e9823' , 'Human Resources Manager'        , 'AL NARJIS - Riyadh'  ,'1210','1126'),
( '0044441111' , 'Turki yasir'     , '1991-02-24' , '20000.00' ,'966556797569' ,'M', 'mohn@gmail.com' , '76324289378' , 'Accountant'                     , 'AL OLAYA - Riyadh'   ,'1230','1121'),
( '0033334444' , 'Maha salem'      , '1999-11-04' , '10000.00' ,'966543526794' ,'F', 'mah1@gmail.com' , '47632987893' , 'Financial Analyst'              ,'AL BALAD - Jeddah'    ,'1410','1121'),
( '0099998888' , 'Fahad muhamad'   , '1989-03-15' , '10000.00' ,'966554378732' ,'M', 'fah1@gmail.com' , '63413783732' , 'Finance Assistant'              , 'AR RABAI - Riyadh'   ,'1240','1121'),
( '0022221111' , 'Sami ali'        , '1997-10-02' , '20000.00' ,'966537654532' ,'M', 'sam@gmail.com'  , '67314673249' , 'Accountant'                     , 'AL MANAR - Dammam'   ,'1310','1121'),
( '0099995555' , 'Danah tariq'     , '1996-01-01' , '15000.00' ,'966531243432' ,'F', 'dan@gmail.com'  , '57646724673' , 'Actuary'                        , 'AL FALAH - Jeddah'   ,'1410','1126'),
( '0033331111' , 'Abdullah ahmad'  , '1989-07-23' , '20000.00' ,'966544322344' ,'M', 'abd1@gmail.com' , '76367326235' , 'human resources administrator'  , 'AL AQIQ - Riyadh'    ,'1220','1126'),
( '0088881111' , 'Saad muhammad'   , '1999-08-12' , '15000.00' ,'966532115532' ,'M', 'saa@gmail.com'  , '71180923623' , 'Customer service representative', 'AL FAISALIH - DAMMAM','1310','1125'),
( '0077774444' , 'Noha khalid'     , '1994-10-12' , '10000.00' ,'966533887557' ,'F', 'nor@gmail.com'  , '61972634872' , 'human resources administrator'  , 'AL BALAD - JEDDAH'   ,'1420','1126'),
( '0033339999' , 'Maha aziz'       , '1997-02-17' , '15000.00' ,'966565355132' ,'F', 'mah@gmail.com'  , '82683637823' , 'Security systems administrator' , 'KING FAHAD - Dammam' ,'1320','1122'),
( '0099991111' , 'Mazen rashed'    , '1994-05-18' , '20000.00' ,'966599255232' ,'M', 'maz@gmail.com'  , '67276287192' , 'IT support specialist'          , 'AL RAWDAH - Riyadh'  ,'1240','1122'),
( '0077772222' , 'Fahad fares'     , '1992-11-10' , '20000.00' ,'966535225762' ,'M', 'fah@gmail.com'  , '23767887294' , 'Database administrator'         , 'AL RAWDAH - Riyadh'  ,'1230','1123'),
( '0055551111' , 'Shahad abdullah' , '1998-03-23' , '10000.00' ,'966534663732' ,'F', 'sha@gmail.com'  , '81276238874' , 'Customer service representative', 'AL BALAD - JEDDAH'   ,'1420','1125');

INSERT INTO `CUSTOMER` ( `NID` , `NAME` , `DOB` , `EMAIL` , `MOBILE` , `ADDRESS` ) VALUES
( '1111222244' , 'Ali Ahmed'    , '2000-02-03', 'ali@gmail.com'     , '966505433911' , 'AL AZIZAIH - Jeddah' ),
( '2222666655' , 'Bader Turki'  , '1999-06-08', 'bader@hotmail.com' , '966546775681' , 'AL AZIZAIH - Dammam' ),
( '3333888866' , 'Hind Faleh'   , '2001-10-23', 'hind@gmail.com'    , '966564566488' , 'ALFALAH - Jeddah'    ),
( '4444333322' , 'Sara Abdullah', '1997-01-05', 'sara@gmail.com'    , '966503456741' , 'ALMALQA - Riyadh'    ),
( '5555999944' , 'Ahmed Fahad'  , '1991-11-02', 'ahmad@yahoo.com'   , '966523455911' , 'AN NADA - Riyadh'    ),
( '6666333377' , 'Ali Omar'     , '1981-09-04', 'aliq@hotmail.com'  , '966505434567' , 'AL FAISALIH - Dammam'),
( '7777888811' , 'Norah Fahad'  , '2002-07-08', 'norah@yahoo.com'   , '966503456789' , 'AL BALAD - Jeddah'   ),
( '8888555533' , 'Khalid Saad'  , '1995-02-17', 'khalid@gmail.com'  , '966505123474' , 'AL MANAR - Dammam'   ),
( '9999111155' , 'Asma Salem'   , '2000-03-03', 'asma@gmail.com'    , '966502345678' , 'ALAQIQ - Riyadh'     ),
( '6666222211' , 'Rand Ali'     , '1991-04-13', 'rand@hotmail.com'  , '966505433556' , 'AL OLAYA - Riyadh'   );

INSERT INTO `BANK`.`ACCOUNT`(  `IBAN` , `Balance` , `Customer ID` , `STATUS` , `TYPE` , `BRANCH#` ) VALUES 
('SA3782224631005321655489' , '1000000.00'  ,  '4444333322' , 'ACTIVE'   , 'SAVING'         , '1210' ),
('SA5432267431012468655487' , '120000.00'   ,  '2222666655' , 'ACTIVE'   , 'SAVING'         , '1310' ),
('SA6632146660573218888485' , '500000.00'   ,  '1111222244' , 'ACTIVE'   , 'SAVING'         , '1410' ),
('SA3782212367758321231579' , '100000.00'   ,  '5555999944' , 'ACTIVE'   , 'CURRENT'        , '1220' ),
('SA2112223451877659053566' , '20000.00'    ,  '9999111155' , 'ACTIVE'   , 'INVESTMENT'     , '1230' ),
('SA5367548431015458765327' , '50000.00'    ,  '6666222211' , 'ACTIVE'   , 'FIXED DEPOSITS' , '1240' ),
('SA8834324667655388566225' , '2000.00'     ,  '6666333377' , 'INACTIVE' , 'INVESTMENT'     , '1320' ),
('SA3234554343545832145676' , '70000.00'    ,  '8888555533' , 'ACTIVE'   , 'CURRENT'        , '1330' ),
('SA1123455751855443989473' , '10000.00'    ,  '3333888866' , 'ACTIVE'   , 'FIXED DEPOSITS' , '1420' ),
('SA4432876888847783345855' , '3000.00'     ,  '7777888811' , 'ACTIVE'   , 'INVESTMENT'     , '1430' ),
('SA8834324667885439202466' , '40000.00'    ,  '5555999944' , 'ACTIVE'   , 'CURRENT'        ,  NULL  ),
('SA4365007753545000065353' , '60000.00'    ,  '4444333322' , 'INACTIVE' , 'FIXED DEPOSITS' , '1210' );

INSERT INTO `BANK`.`BRANCH DEPARTMENTS` ( `DEPARTMENT#` ,  `BRANCH#` ) VALUES 
('1121' ,'1210' ), ('1122' ,'1210' ), ('1123' ,'1210' ), ('1124' ,'1210' ),
('1125' ,'1210' ), ('1126' ,'1210' ), ('1121' , '1220'), ('1122' , '1220'),
('1123' , '1220'), ('1124' , '1220'), ('1125' , '1220'), ('1126' , '1220'),
('1121' , '1230'), ('1122' , '1230'), ('1123' , '1230'), ('1124' , '1230'),
('1125' , '1230'), ('1126' , '1230'), ('1121' , '1240'), ('1122' , '1240'),
('1121' , '1310'), ('1122' , '1310'), ('1123' , '1310'), ('1124' , '1310'),
('1125' , '1310'), ('1126' , '1310'), ('1121' , '1320'), ('1122' , '1320'),
('1123' , '1320'), ('1124' , '1320'), ('1125' , '1320'), ('1126' , '1320'),
('1121' , '1330'), ('1122' , '1330'), ('1123' , '1330'), ('1124' , '1330'), 
('1125' , '1330'), ('1126' , '1330'), ('1121' , '1410'), ('1122' , '1410'),
('1123' , '1410'), ('1124' , '1410'), ('1125' , '1410'), ('1126' , '1410'),
('1121' , '1420'), ('1122' , '1420'), ('1123' , '1420'), ('1124' , '1420'),
('1123' , '1430'), ('1124' , '1430'), ('1125' , '1430'), ('1126' , '1430');

INSERT INTO `BANK`.`CARD`( `CARD#`, `IBAN` , `Expiration Date` , `CVV`, `TYPE`) VALUES 
( '3742454553400126' , 'SA3782224631005321655489' , '2024-07-08' , '272' , 'CREDIT' ),
( '6643454594320129' , 'SA5432267431012468655487' , '2023-04-02' , '333' , 'CREDIT' ),
( '1742450054231686' , 'SA6632146660573218888485' , '2022-07-20' , '762' , 'CREDIT' ),
( '6744345553754432' , 'SA3782212367758321231579' , '2024-10-08' , '854' , 'DEPIT'  ),
( '5200533989552126' , 'SA2112223451877659053566' , '2023-01-18' , '112' , 'CREDIT' ),
( '4917484589875443' , 'SA5367548431015458765327' , '2024-03-20' , '773' , 'DEPIT'  ),
( '6453275539813478' , 'SA8834324667655388566225' , '2023-05-08' , '842' , 'PREPAID'),
( '6744345512345328' , 'SA3234554343545832145676' , '2024-11-03' , '896' , 'DEPIT'  ),
( '3300065455753279' , 'SA1123455751855443989473' , '2023-07-22' , '196' ,'TRAVELER'),
( '2786650764435305' , 'SA4432876888847783345855' ,'2022-12-01'  , '697' , 'DEPIT'  ),
( '7864755238133423' , 'SA8834324667885439202466' , '2023-06-11' , '122' , 'PREPAID'),
( '1245434124527657' , 'SA4365007753545000065353' , '2022-11-08' , '875' ,'TRAVELER');

INSERT INTO `BANK`.`DEPENDANT`( `NAME` ,`Employee ID` , `DOB` , `Relationship` , `SEX` ) VALUES 
( 'Rami' ,'0055554444' , '1987-08-09' , 'spouse' ,'M' ), 
( 'Nuha' ,'0066661111' , '1970-11-09' , 'parent' ,'F' ),
( 'Sara' ,'0066667777' , '1985-02-09' , 'spouse' ,'F' ),
( 'Norah','0066665555' , '1970-11-09' , 'spouse' ,'F' ),
( 'Haifa','0022224444' , '2008-08-09' , 'Child'  ,'F' ),
( 'Saad' ,'0022224444' , '2010-03-09' , 'Child'  ,'M' ),
( 'Omar' ,'0099995555' , '1970-11-09' , 'parent' ,'M' ),
( 'Turki','0066668888' , '1972-09-08' , 'parent' ,'M' ),
( 'Hessa','0022227777' , '1960-12-09' , 'parent' ,'F' ),
( 'Dina' ,'0044443333' , '2016-03-02' , 'Child'  ,'F' );

INSERT INTO `BANK`.`LOAN`(`LOAN#` ,`BRANCH#` ,`TYPE` , `Interest Rate`  , `DATE`, `Customer ID` , `Amount` , `Paid Amount` ) VALUES
 ( DEFAULT ,  '1210'   ,  'Personal  Finance'   , '0.10' , '2020-03-20', '4444333322' , '500000.00' , '100000.00' ),
 ( DEFAULT ,  '1220'   ,  'Personal  Finance'   , '0.30' , '2022-04-20', '5555999944' , '70000.00'  ,    '0.0'    ),
 ( DEFAULT ,  '1230'   ,  'Personal  Finance'   , '0.50' , '2021-09-20', '9999111155' , '50000.00'  , '15000.00'  ),
 ( DEFAULT ,  '1240'   ,  'Home Equity Loan'    , '0.20' , '2019-01-10', '6666222211' , '450000.00' , '252347.50' ),
 ( DEFAULT ,  '1310'   ,  'Home Equity Loan'    , '0.20' , '2019-01-10', '2222666655' , '600000.00' , '600000.00' ),
 ( DEFAULT ,  '1320'   ,  'Mortgage Loan'       , '0.40' , '2019-07-10', '3333888866' , '100000'    ,    '50.0'   ),
 ( DEFAULT ,  '1320'   ,  'Small Business Loan' , '0.65' , '2021-12-20', '6666333377' , '200000.00' , '50000.00'  ),
 ( DEFAULT ,  '1330'   ,  'Car Loan'            , '0.30' , '2020-03-03', '8888555533' , '50000.00'  , '15000.00'  ),
 ( DEFAULT ,  '1410'   ,  'Car Loan'            , '0.30' , '2021-08-03', '1111222244' , '70000.00'  ,    '0.0'    ),
 ( DEFAULT ,  '1420'   ,  'Benevolent Loan'     , '0.00' , '2021-06-08', '3333888866' , '15000.00'  , '9300.00'   ),
 ( DEFAULT ,  '1320'   ,  'Refinance'           , '0.75' , '2019-01-11', '8888555533' , '100000'    ,    '0.0'    ),
 ( DEFAULT ,  '1430'   ,  'Real Estate Loan'    , '0.25' , '2018-03-20', '7777888811' , '500000.00' , '252650.00' );

INSERT INTO `BANK`.`TRANSACTION`(`Reference#`, `TYPE`, `TIME STAMP`,`Currency`,`Amount`, `BRANCH#` , `Sender IBAN` , `CARD#` ,`Receiver IBAN`,`COMMENTS`) VALUES 
(DEFAULT , 'withdrawal'        , '2021-08-09 13:57:40' ,'SAR','1200.00' ,'1210','SA3782224631005321655489','3742454553400126', NULL , NULL ),
(DEFAULT , 'deposits'          , '2022-01-04 11:36:10' ,'SAR','700.00'  ,'1220','SA3782212367758321231579','6744345553754432', 'SA4365007753545000065353', 'salary' ),
(DEFAULT , 'transfer'          , '2021-08-10 12:13:44' ,'JPY','50000.00','1230','SA8834324667885439202466','5200533989552126', 'JP6010096000505719826457', 'eidia' ),
(DEFAULT , 'online payment'    , '2022-02-11 11:20:40' ,'USD','1000.00' , NULL ,'SA3234554343545832145676','6744345512345328', 'US2812645415199993394689', 'online shpping' ),
(DEFAULT , 'debit card charges', '2022-03-09 10:50:41' ,'SAR','5000.00' , NULL ,'SA6632146660573218888485','1742450054231686', NULL , NULL );

UPDATE `DEPARTMENT` SET `MANAGER ID` = '0055555432' WHERE `DEPARTMENT#` = '0001';
UPDATE `DEPARTMENT` SET `MANAGER ID` = '0044443333' WHERE `DEPARTMENT#` = '1122';
UPDATE `DEPARTMENT` SET `MANAGER ID` = '0066669999' WHERE `DEPARTMENT#` = '1121';
UPDATE `DEPARTMENT` SET `MANAGER ID` = '0022227777' WHERE `DEPARTMENT#` = '1123';
UPDATE `DEPARTMENT` SET `MANAGER ID` = '0066661111' WHERE `DEPARTMENT#` = '1124';
UPDATE `DEPARTMENT` SET `MANAGER ID` = '0066665555' WHERE `DEPARTMENT#` = '1125';
UPDATE `DEPARTMENT` SET `MANAGER ID` = '0066667777' WHERE `DEPARTMENT#` = '1126';

UPDATE `BRANCH` SET `MANAGER ID` = '0022224444' WHERE `BRANCH#` = '1210';
UPDATE `BRANCH` SET `MANAGER ID` = '0033337777' WHERE `BRANCH#` = '1220';
UPDATE `BRANCH` SET `MANAGER ID` = '0055554444' WHERE `BRANCH#` = '1230';
UPDATE `BRANCH` SET `MANAGER ID` = '0033335555' WHERE `BRANCH#` = '1240';
UPDATE `BRANCH` SET `MANAGER ID` = '0066664444' WHERE `BRANCH#` = '1310';
UPDATE `BRANCH` SET `MANAGER ID` = '0033336666' WHERE `BRANCH#` = '1320';
UPDATE `BRANCH` SET `MANAGER ID` = '0066668888' WHERE `BRANCH#` = '1330';
UPDATE `BRANCH` SET `MANAGER ID` = '0055552222' WHERE `BRANCH#` = '1410';
UPDATE `BRANCH` SET `MANAGER ID` = '0099997777' WHERE `BRANCH#` = '1420';
UPDATE `BRANCH` SET `MANAGER ID` = '0022226666' WHERE `BRANCH#` = '1430';
/*
-- tables before selected-users' queries:
select * from `CUSTOMER`;
select * from `BRANCH`;
select * from `DEPARTMENT`;
select * from `ACCOUNT`;
select * from `CARD`;
select * from `DEPENDANT`;
select * from `LOAN`;
select * from `EMPLOYEE`;
select * from `TRANSACTION`;
-- select `name`, `nid`, `DOB`, `salary`, `mobile`, `job title` from `employee`;
-- select `Reference#`, `time stamp`, `type`, `currency`, `amount`, `sender IBAN`, `comments` from `transaction`;
*/
-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------

-- 1\operations by customer service employees:
-- insert
insert into `CUSTOMER`
values ('1234567890','Nasser Ali', '2000-02-2' , 
'Ali@gmail.com','966501234567','AlOlya - Riyadh' );

insert into `LOAN` 
values ( default ,  '1210'   ,  'Car Loan'  , '0.25' ,
 '2020-01-15', '1111222244' , '50000.00'  , '10000.00');

-- delete
delete from `LOAN` 
where `Amount` = `Paid Amount` and `LOAN#` = '5';

delete from `ACCOUNT`
where  `CUSTOMER ID` = '2222666655';

-- update
update `ACCOUNT`
set `STATUS` = 'INACTIVE'
where `CUSTOMER ID` = '7777888811';

update `LOAN`
set `Paid Amount` = '6000.0'
where `LOAN#` = '2';

-- Retrieve from one table 
select `customer`.`name`, `customer`.`mobile` 
from `CUSTOMER`
where `NID` = '4444333322';

select `customer`.`name`, `customer`.`mobile` 
from `customer`, `account` 
where `nid`=`customer id` and `account`.`type` = 'investment';


-- Retrieve from more than two tables
select `NAME` , `MOBILE` , `BALANCE` as `account balance` , `ACCOUNT`.`Type` as `account type`
from ((`CUSTOMER` join `ACCOUNT` on `CUSTOMER`.`NID` = `account`.`Customer ID` )
 join `CARD` on `CARD`.`IBAN`=`ACCOUNT`.`IBAN`);

select `NAME`, `LOAN`.`Amount` as `loan amount`, `LOAN`.`TYPE` as `loan type`
from ((`CUSTOMER` join `LOAN` on `customer`.`NID` = `loan`.`Customer ID` and `TYPE` = 'Refinance' )
 join `ACCOUNT` on `customer`.`NID` = `account`.`Customer ID`);
 
-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------
 
-- 2\operations by the customer:
-- insert
insert into `transaction` (`Reference#`, `TYPE`, `TIME STAMP`,`Currency`, `Amount` , `Sender IBAN` , `CARD#` ,`Receiver IBAN`,`COMMENTS`) 
values (default , 'deposits' , '2019-01-12 12:43:55' ,'SAR','15000.00' , 'SA3782224631005321655489','3742454553400126', 'SA4365007753545000065353', 'personal deposits');

insert into `CARD`
values ('3745637213400126' , 'SA3782224631005321655489' , '2026-01-08' , '101' , 'DEPIT' );

-- delete
delete from `CARD`
where  `Card#` = '2786650764435305';

delete from  `ACCOUNT`
where `IBAN` = 'SA3234554343545832145676';

-- update
update `CUSTOMER`
set `ADDRESS` = 'AL SAHAFA - Riyadh'
where `NID` = '7777888811';

update `CUSTOMER`
set `MOBILE` = '966500273498'
where `NID` = '7777888811';

-- Retrieve from one table 
select `IBAN`, `BALANCE`
from `ACCOUNT`
where `customer ID` = '4444333322';

select `Reference#`, `Time Stamp`
from `TRANSACTION`
where `Sender IBAN` = 'SA3782212367758321231579';

-- Retrieve from more than two tables
SELECT `CUSTOMER`.`NID` , `CUSTOMER`.`NAME` , `ACCOUNT`.`IBAN`  , `CARD`.`CARD#`
FROM ((`CUSTOMER` JOIN `ACCOUNT` ON `CUSTOMER`.`NID` = `ACCOUNT`.`Customer ID`) 
JOIN `CARD` ON `ACCOUNT`.`IBAN` = `CARD`.`IBAN`)
where `nid` = '4444333322';


SELECT `CUSTOMER`.`NID` , `CUSTOMER`.`NAME` , `ACCOUNT`.`IBAN`  , `TRANSACTION`.`CARD#`, `TRANSACTION`.`TYPE` , `TRANSACTION`.`Amount`
FROM `CUSTOMER`
JOIN `ACCOUNT`
ON `CUSTOMER`.`NID` = `ACCOUNT`.`Customer ID`
JOIN `TRANSACTION`
ON `ACCOUNT`.`IBAN` = `TRANSACTION`.`Sender IBAN`
where `nid` = '4444333322';

-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------

 -- 3\operations by ceo:
 -- insert
insert into `EMPLOYEE`
values('2222222222' , 'Layla abdullah', '1999-09-27' , '150000.00','966550173846' ,'F', 'lll456@gmail.com' , '66666666666' , 'Actuary' , 'AL NADA - RIYADH'  ,'1420','1126');
 
insert into `BRANCH`
values( '4444' ,  '0055554444' , 'AL SAHAFA - Riyadh');

-- delete
delete from `DEPENDANT`
where `Employee ID` = '0066668888' and `name` = 'Turki';

delete from `EMPLOYEE`
where `NID` = '0044443333';

-- update
update `EMPLOYEE`
set `Job Title` = 'IT manager'
where `NID` = '0055554444';

update `EMPLOYEE`
set `Department#` = '1122'
where `NID` = '0055554444';

update `EMPLOYEE`
set `SALARY` = '35000.00'
where `NID` = '0055554444';

update `DEPARTMENT` 
set `MANAGER ID` = '0055554444' 
where `DEPARTMENT#` = '1122';

-- Retrieve from one table 
select `name`,`Customer ID`
from `ACCOUNT`, `customer` 
where `Branch#` = '1320' and `NID` = `Customer ID`; 

select `Customer ID`, `date` as `loan date`
from `loan` 
where `interest rate` > 0.25;

select `NAME`, `SALARY`
from `EMPLOYEE`
where `Branch#` = '1210';

-- Retrieve from more than two table
select `EMPLOYEE`.`NAME`, `Job Title`, `DEPENDANT`.`NAME` as `dependant name`, `Relationship`, `Department Name` 
from ((`EMPLOYEE` join `DEPENDANT` on `NID` = `Employee ID`  )
 join `DEPARTMENT` on `DEPARTMENT`.`Department#` = `EMPLOYEE`.`Department#` and `DEPARTMENT`.`Department#` = '0001' );

select `loan`.`CUSTOMER ID`, `AMOUNT` as `loan amount`, `DATE` as `loan date`, `LOCATION` as `branch location` , `MOBILE`
from ((`CUSTOMER` join `LOAN` on `NID` = `loan`.`CUSTOMER ID`)
 join `BRANCH` on `LOAN`.`Branch#` = `branch`.`Branch#`) ;

-- -------------------------------------------------------------------------

create view `branch employees` as
select count(*) as `number of employees`, `branch`.`branch#`
from `employee`, `branch`
where  `branch`.`branch#`=`employee`.`branch#`
group by `branch`.`branch#`;

select * from `branch employees`;

/*
-- tables after selected-users' queries:
select * from `CUSTOMER`;
select * from `BRANCH`;
select * from `DEPARTMENT`;
select * from `ACCOUNT`;
select * from `CARD`;
select * from `DEPENDANT`;
select * from `LOAN`;
select name, nid, DOB, salary, mobile, `job title` from employee;
select `Reference#`, `time stamp`, `type`, currency, amount, `sender IBAN`, comments from transaction;*/