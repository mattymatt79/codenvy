/*
 *
 * CODENVY CONFIDENTIAL
 * ________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */

 IMPORT 'macros.pig';

l = loadResources('$LOG', '$USER', '$WS');

a1 = filterByEvent(l, 'user-update-profile');
a2 = filterByDate(a1, '$FROM_DATE', '$TO_DATE');

a4 = extractParam(a2, 'FIRSTNAME', 'firstName');
a5 = extractParam(a4, 'LASTNAME', 'lastName');
a6 = extractParam(a5, 'COMPANY', 'company');
a7 = extractParam(a6, 'PHONE', 'phone');
a8 = extractParam(a7, 'JOBTITLE', 'job');
a = FOREACH a8 GENERATE user, firstName, lastName, company, phone, job;

r1 = FILTER a BY firstName != '' AND firstName != 'null' AND firstName IS NOT NULL AND
		lastName != '' AND lastName != 'null' AND lastName IS NOT NULL AND 
		company != '' AND company != 'null' AND company IS NOT NULL AND
		phone != '' AND phone != 'null' AND phone IS NOT NULL AND
		job != '' AND job != 'null' AND job IS NOT NULL;

result = FOREACH r1 GENERATE TOTUPLE(TOTUPLE(user), TOTUPLE(firstName), TOTUPLE(lastName), TOTUPLE(company), TOTUPLE(phone), TOTUPLE(job));

