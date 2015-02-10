DROP TABLE T_PROJECT_T_TECHNOLOGY;
DROP TABLE T_PROJECT;
DROP TABLE T_COMPANY;
DROP TABLE T_TECHNOLOGY;

CREATE TABLE T_TECHNOLOGY (id BIGINT NOT NULL, name VARCHAR(255), description VARCHAR(255), CONSTRAINT PK_T_TECHNOLOGY PRIMARY KEY (id));
CREATE TABLE T_COMPANY (id BIGINT NOT NULL, name_en VARCHAR(255), name_hu VARCHAR(255), description_en VARCHAR(1000), description_hu VARCHAR(1000), period_en VARCHAR(255), period_hu VARCHAR(255), CONSTRAINT PK_T_COMPANY PRIMARY KEY (id));
CREATE TABLE T_PROJECT (id BIGINT NOT NULL, name_en VARCHAR(255), name_hu VARCHAR(255), description_en VARCHAR(1000), description_hu VARCHAR(1000), client_en VARCHAR(255), client_hu VARCHAR(255), start date, end date, company_id BIGINT, CONSTRAINT PK_T_PROJECT PRIMARY KEY (id));
ALTER TABLE T_PROJECT ADD CONSTRAINT fk_project_company_id FOREIGN KEY (company_id) REFERENCES T_COMPANY (id);
CREATE TABLE T_PROJECT_T_TECHNOLOGY (technologys_id BIGINT NOT NULL, projects_id BIGINT NOT NULL, CONSTRAINT PK_T_PROJECT_T_TECHNOLOGY PRIMARY KEY (technologys_id, projects_id));
ALTER TABLE T_PROJECT_T_TECHNOLOGY ADD CONSTRAINT fk_project_technology_id FOREIGN KEY (technologys_id) REFERENCES T_TECHNOLOGY (id);
ALTER TABLE T_PROJECT_T_TECHNOLOGY ADD CONSTRAINT fk_technology_project_id FOREIGN KEY (projects_id) REFERENCES T_PROJECT (id);

INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('1', 'J2EE', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('2', 'Spring', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('3', 'Hibernate', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('4', 'Spring MVC', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('5', 'AngularJS', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('6', 'Bootstrap', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('7', 'MySQL', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('8', 'Java', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('9', 'Spring', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('10', 'Hazelcast', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('11', 'LMAX', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('12', 'RabbitMQ', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('13', 'Wicket', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('14', 'Greenplum', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('15', 'Activiti', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('16', 'MSSQL', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('17', 'PostgreSQL', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('18', 'EclipseLink', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('19', 'Oracle', NULL);
INSERT INTO T_TECHNOLOGY (id, name, description) VALUES ('20', 'Struts', NULL);

INSERT INTO T_COMPANY (id, name_en, name_hu, period_en, period_hu, description_en, description_hu) VALUES ('4', 'Sonrisa International Inc.','Sonrisa Informatikai Kft.', '2009.09 - current', '2009.09 - Jelenleg', 
'Project based company delivering custom software development projects. My role was usally system analyst, product owner sometimes project manager...',
'Egyedi szoftver fejlesztésre alapuló informatikai cégnél rendszertervező, üzleti elemző, és esetenként projektvezető szerepkörök ellátása. A projektek teljes életútjának támogatása, illetve a megvalósításban való részvétel, egészen a fejlesztési folyamatokba való bekapcsolódásig. UI tervezés, séma tervezés, felület implementáció, unit tesztelés...');
INSERT INTO T_COMPANY (id, name_en, name_hu, period_en, period_hu, description_en, description_hu) VALUES ('3', 'Budapest Bank Inc.','Budapest Bank Zrt.', '2006.09 - 2008.08', '2006.09 - 2008.08', 
'Project based company delivering custom software development projects. My role was usally system analyst, product owner sometimes project manager...',
'Informatikai projektvezetési tevékenységek ellátása, az üzleti oldallal való folyamatos kapcsolattartás, illetve a komplex banki informatikai környezetben az üzleti igények adaptálása, esetleges rendszertervezési feladatok elvégzésével.');
INSERT INTO T_COMPANY (id, name_en, name_hu, period_en, period_hu, description_en, description_hu) VALUES ('2', 'GE Money Bank Thailand, Bangkok','GE Money Bank Thailand, Bangkok', '2006.02 - 2006.08', '2006.02 - 2006.08', 
'Project based company delivering custom software development projects. My role was usally system analyst, product owner sometimes project manager...',
'Új hitelkártya bevezetését megvalósító projektben informatikai projektvezető, és a hitelkártya elfogadó termináloktól érkező tranzakciók kezelését végző szoftverrel kapcsolatos rendszertervezési feladatok ellátása.');
INSERT INTO T_COMPANY (id, name_en, name_hu, period_en, period_hu, description_en, description_hu) VALUES ('1', 'General Electric - Budapest Bank Inc','General Electric - Budapest Bank Zrt.', '2004.02 - 2006.02', '2004.02 - 2006.02', 
'Project based company delivering custom software development projects. My role was usally system analyst, product owner sometimes project manager...',
'A General Electric informatikai projektvezető képző programjában való részvétel, amely a szakmai képzések mellett tényleges projektek végrehajtását jelentette.');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('2', 'ACFC','ACFC', 'Internal project', 'Belső projekt', '2004-06-01', '2005-06-01', '1',
'Project english description..',
'(At the Customer For the Customer): A jelzáloghitelezési folyamatban a mobil ügyintézők tevékenységét megkönnyítő központi dokumentum menedzsment megoldás bevezetésében informatikai projektvezető szerepkör');
INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('1', 'IdED','IdED', 'Internal project', 'Belső projekt', '2005-06-01', '2005-12-01', '1',
'Project english description..',
'(IDentity Entitlement Directory): Központi jogosultságkezelés bevezetését előkészítését megcélzó pilot projektben informatikai projektvezető szerepkör');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('5', 'PBR','PBR', 'Internal project', 'Belső projekt', '2006-08-01', '2007-06-01', '3',
'Project english description..',
'(Primary Banking Relationship): A kiemelt banki ügyfelek számára nyújtott csomagok bevezetését támogató projektben informatikai projektvezető és rendszerszervező szerepkör');
INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('4', 'OJK','OJK', 'Internal project', 'Belső projekt', '2007-01-01', '2007-12-01', '3',
'Project english description..',
'(Ország- és Jogcímkód): Az ország- és jogcímkód kezelésének törvényi változásait lekövető projektben informatikai projektvezető és rendszerszervező szerepkör');
INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('3', 'CNC','CNC', 'Internal project', 'Belső projekt', '2008-01-01', '2008-08-01', '3',
'Project english description..',
'(Customer Number Cleaning): A fő banki számlavezető rendszer ügyféltörzsében felmerült azonosító képzési problémákat kezelő projektben informatikai projektvezető szerepkör');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('13', 'KEKK-NLR','KEKK-NLR', 'KEKK-KH', 'KEKK-KH', '2008-08-01', '2009-03-01', '4',
'Project english description..',
'(Napló Rendszer): Közigazgatási és Elektronikus Közszolgáltatások Központi Hivatalában az üzleti tevékenységek napló állományainak elemzését lehetővé tévő megoldás bevezetése.');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('13', '8');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('13', '9');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('13', '20');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('13', '19');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('12', 'KGR EGM','KGR EGM', 'Hungarian Treasury', 'Magyar Állam Kincstár', '2009-03-01', '2010-08-01', '4',
'Project english description..',
'(Költségvetés Gazdálkodási Rendszer Előirányzat gazdálkodás modul): A Magyar Államkincstár belső rendszereinek és működésének megújítását célzó KGR projektben az Előirányzat gazdálkodási modul folyamatainak és funkcióinak specifikálása.');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('11', 'KGR K11','KGR K11', 'Hungarian Treasury', 'Magyar Állam Kincstár', '2010-06-01', '2011-03-01', '4',
'Project english description..',
'(Költségvetés Gazdálkodási Rendszer Adatszolgáltató modul): régi K11 beszámoló rendszer leváltása egy korszerű és rugalmas központi informatikai megoldással.');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('11', '8');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('11', '9');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('11', '18');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('11', '13');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('11', '19');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('10', 'IDM','IDM', 'Prosecutor General Office', 'Legfőbb Ügyészség', '2011-02-01', '2011-10-01', '4',
'Project english description..',
'A Legfőbb Ügyészség szervezetében az egységesített jogosultságkezelési folyamatokat támogató informatikai megoldás kidolgozása.');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('10', '8');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('10', '9');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('10', '3');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('10', '13');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('10', '16');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('9', 'Smart Traffic','Smart Traffic', 'Budapest Technical University', 'BME Út és Vasútépítési Tanszék', '2014-02-01', '2014-05-01', '4',
'Project english description..',
'BME Út és Vasútépítési Tanszék számára forgalmi és útburkolat minőségi adatok interaktív térképi megjelenítése.');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('9', '8');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('9', '4');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('9', '5');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('9', '17');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('8', 'JOKER','JOKER', 'T-Systems Hungary Inc.', 'T-Systems Magyarország Zrt.', '2012-06-01', '2014-10-01', '4',
'Project english description..',
'(Jogosultságkezelő Rendszer): T-Systems Magyarország Zrt.-ben új jogosultságkezelő rendszer bevezetése.');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('8', '8');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('8', '9');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('8', '3');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('8', '15');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('8', '13');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('8', '16');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('7', 'MI6','MI6', 'Voodoopark', 'Voodoopark', '2014-06-01', '2014-09-01', '4',
'Project english description..',
'Telekom szolgáltató számára lokáció alapú, valós idejű mobil hírdetési rendszer informatikai infrastruktúrájának lefektetése.');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('7', '8');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('7', '9');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('7', '10');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('7', '11');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('7', '12');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('7', '13');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('7', '14');

INSERT INTO T_PROJECT (id, name_en, name_hu, client_en, client_hu, start, end, company_id, description_en, description_hu) VALUES ('6', 'Cabi Dashboard','Cabi Dashboard', 'Cabi Online', 'Cabi Online', '2014-10-01', '2015-03-01', '4',
'Project english description..',
'MLM ügyfél számára célkitűzés karbantartó, illetve tény és terv adatok vizuális összevetését kiszolgáló dashboard kidolgozása.');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('6', '8');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('6', '1');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('6', '3');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('6', '4');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('6', '5');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('6', '6');
INSERT INTO T_PROJECT_T_TECHNOLOGY (projects_id, technologys_id) VALUES ('6', '7');


DELETE FROM hibernate_sequences WHERE sequence_name = 'T_TECHNOLOGY';
DELETE FROM hibernate_sequences WHERE sequence_name = 'T_COMPANY';
DELETE FROM hibernate_sequences WHERE sequence_name = 'T_PROJECT';
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('T_TECHNOLOGY', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('T_COMPANY', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('T_PROJECT', 1);


