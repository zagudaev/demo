const recursiveFind = (event, array, childKey) => {
  const findResult = array.find(event);
  if (findResult) return findResult;

  for (let i = 0; i < array.length; i += 1) {
    return recursiveFind(event, array[i][childKey], childKey);
  }

  return -1;
};

export default recursiveFind;
