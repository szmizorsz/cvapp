mongo ds053678.mongolab.com:53678/CloudFoundry_nnh6bdc7_81u9pqf9 -u CloudFoundry_nnh6bdc7_81u9pqf9_k7r0hgv0 -p Ka3YguvGj9sfN-z4d_joHuCGLEBinLs7 --eval "db.T_OTHER.drop(); db.T_EDUCATION.drop(); db.T_KNOWLEDGE.drop(); db.T_LANGUAGE.drop();"
mongorestore -h ds053678.mongolab.com:53678 -d CloudFoundry_nnh6bdc7_81u9pqf9 -c T_EDUCATION -u CloudFoundry_nnh6bdc7_81u9pqf9_k7r0hgv0 -p Ka3YguvGj9sfN-z4d_joHuCGLEBinLs7 T_EDUCATION.bson
mongorestore -h ds053678.mongolab.com:53678 -d CloudFoundry_nnh6bdc7_81u9pqf9 -c T_KNOWLEDGE -u CloudFoundry_nnh6bdc7_81u9pqf9_k7r0hgv0 -p Ka3YguvGj9sfN-z4d_joHuCGLEBinLs7 T_KNOWLEDGE.bson
mongorestore -h ds053678.mongolab.com:53678 -d CloudFoundry_nnh6bdc7_81u9pqf9 -c T_LANGUAGE -u CloudFoundry_nnh6bdc7_81u9pqf9_k7r0hgv0 -p Ka3YguvGj9sfN-z4d_joHuCGLEBinLs7 T_LANGUAGE.bson
mongorestore -h ds053678.mongolab.com:53678 -d CloudFoundry_nnh6bdc7_81u9pqf9 -c T_OTHER -u CloudFoundry_nnh6bdc7_81u9pqf9_k7r0hgv0 -p Ka3YguvGj9sfN-z4d_joHuCGLEBinLs7 T_OTHER.bson

