import { IMemberRegister, NewMemberRegister } from './member-register.model';

export const sampleWithRequiredData: IMemberRegister = {
  id: 64509,
  lastName: 'Lebsack',
  firstName: 'Marlee',
  surName: 'copying',
  registerDate: 'Generic SD',
  phoneNumber: '0630810390',
  email: 'Coralie.Crist1@gmail.com',
};

export const sampleWithPartialData: IMemberRegister = {
  id: 77148,
  lastName: 'Auer',
  firstName: 'Mavis',
  surName: 'hack Loan Carolina',
  registerDate: 'Concrete',
  memberNumber: '2303001',
  phoneNumber: '0630810393',
  adresse: 'Principal mobile',
  email: 'Curt14@gmail.com',
  etat: 'Guinea',
};

export const sampleWithFullData: IMemberRegister = {
  id: 18180,
  lastName: 'Bartoletti',
  firstName: 'Yvette',
  surName: 'Soft',
  registerDate: 'the Garden',
  memberNumber:'2303001',
  phoneNumber:'0630810392',
  adresse: 'Analyst',
  email: 'Rosario65@yahoo.com',
  etat: 'RAM Computers',
};

export const sampleWithNewData: NewMemberRegister = {
  lastName: 'Senger',
  firstName: 'Hester',
  surName: 'revolutionize',
  registerDate: 'Future Con',
  phoneNumber:'0630810394',
  email: 'Pablo78@yahoo.com',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
