import { IContact } from 'app/shared/model/contact.model';

export interface IPerson {
  id?: number;
  name?: string | null;
  contacts?: IContact[] | null;
}

export const defaultValue: Readonly<IPerson> = {};
