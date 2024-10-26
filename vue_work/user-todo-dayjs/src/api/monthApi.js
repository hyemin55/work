import axios from 'axios';

const url = 'http://localhost:20000';

export const saveTodo = async (title, content, selectDate, completed) => {
  try {
    const res = await axios.post(
      `${url}/todo/save`,
      {
        title: title,
        content: content,
        selectDate: selectDate,
        completed: completed,
      },
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      },
    );
    return res;
  } catch (err) {
    console.error(err);
    return err;
  }
};
