import {v1} from 'uuid';

export const uuidExists = () => !!getUUID();

export const setUUID = (uuid) => localStorage.setItem('__uuid__', uuid);
export const getUUID = () => localStorage.getItem('__uuid__');
export const removeUUID = () => localStorage.removeItem('__uuid__');

export const createUUID = () => {
  if (!uuidExists()) {
    const uuidV1 = v1();
    setUUID(uuidV1);
  }
};
