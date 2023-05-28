export interface IMemberRegister {
  id: number;
  lastName?: string | null;
  firstName?: string | null;
  surName?: string | null;
  registerDate?: string | null;
  phoneNumber?: string | null;
  adresse?: string | null;
  email?: string | null;
  etat?: string | null;
}
export interface MemberRegister extends IMemberRegister {
  
  memberNumber?: string | null;
}
  

export type NewMemberRegister = Omit<IMemberRegister, 'id'> & { id: null };
