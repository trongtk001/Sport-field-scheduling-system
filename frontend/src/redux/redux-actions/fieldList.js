export const addFilter = (data) => {
    return {
        type: 'addFilter',
        payload: data
    }
}
 
export const addSearchValue = (data) => {
    return {
        type: 'addSearchValue',
        payload: data
    }
}