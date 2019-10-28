import re
from datetime import datetime


class Person:

    # def __init__(self, name, points, married, date):
    #     self.name = name
    #     self.points = points
    #     self.married = married
    #     self.date = date
    name = ''
    points = 0
    married = False
    date = None

    def display(self):
        print('Name: '+self.name)
        print('Points: '+str(self.points))
        print('date: '+str(self.date))
        print('Married :'+str(self.married))


def find_json(line):
    xd = re.findall('"([A-z0-9śćłżźąę]+)": ["]?([A-z|><:!@#$%^&*0-9/śćłżźąę]*)["]?', line)
    if len(xd) == 0:
        return None
    person = Person()
    for kv in xd:
        if kv[0] == 'name':
            person.name = kv[1]
        elif kv[0] == 'points':
            person.points = kv[1]
        elif kv[0] == 'date':
            person.date = datetime.strptime(kv[1], '%d/%m/%Y')
        elif kv[0] == 'married':
            if kv[1] == 'true':
                person.married = True
            else:
                person.married = False
        # print(kv[0])
        # print(kv[1])
    return person


def find_xml(line):
    xd = re.findall('<([^>]+)>([^<>]+)</([^>]+)>', line)
    if len(xd) == 0:
        return None
    person = Person()
    for kv in xd:
        if kv[0] == 'name':
            person.name = kv[1]
        elif kv[0] == 'points':
            person.points = kv[1]
        elif kv[0] == 'date':
            person.date = datetime.strptime(kv[1], '%d/%m/%Y')
        elif kv[0] == 'married':
            if kv[1] == 'true':
                person.married = True
            else:
                person.married = False

    return person


def find_csv(line):
    xd = re.findall('([^, ]+)', line)

    if len(xd) == 0:
        return None

    person = Person()
    person.name = xd[0]
    person.date = datetime.strptime(xd[1], '%d/%m/%Y')
    person.points = xd[2]
    if xd[3] == 'true':
        person.married = True
    else:
        person.married = False

    return person


def find_space(line):
    xd = re.findall('([^, \n]+)     (\d{2}/\d{2}/\d{4})     (\d+)     (true|false)', line)

    if len(xd) == 0:
        return None

    person = Person()
    person.name = xd[0]
    person.date = datetime.strptime(xd[1], '%d/%m/%Y')
    person.points = xd[2]
    if xd[3] == 'true':
        person.married = True
    else:
        person.married = False

    return person


persons = []
f = open("file.txt")
for x in f:
    p = find_json(x)
    if p is not None:
        persons.append(p)
        continue

    p = find_xml(x)
    if p is not None:
        persons.append(p)
        continue

    p = find_csv(x)
    if p is not None:
        persons.append(p)
        continue

    p = find_space(x)
    if p is not None:
        persons.append(p)
        continue

for p in persons:
    print('--------------------')
    p.display()
