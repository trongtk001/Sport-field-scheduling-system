import { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";

import SearchIcon from '@mui/icons-material/Search';

import { useDispatch } from "react-redux/es/exports";
import { filterSlice } from "../../../redux/redux-reducer/filter";

import classNames from "classnames/bind";
import styles from "./SearchBox.module.scss";
const cx = classNames.bind(styles);

function SearchBox() {
  const [searchValue, setSearchValue] = useState("");

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const inputRef = useRef();

  const handleSearchSubmit = () => {
    if (searchValue) {
      navigate(`/search?q=${searchValue}`);
      dispatch(filterSlice.actions.addSearchValue(searchValue));

      inputRef.current.blur()
      setSearchValue("");
    }
  };

  const handleEnterKeyDown = (e) => {
    if (searchValue && e.key === "Enter") {
      handleSearchSubmit();
    }
  };

  return (
    <div className={cx("search-box")}>
      <input
        ref={inputRef}
        value={searchValue}
        onChange={(e) => setSearchValue(e.target.value.trim())}
        type="text"
        placeholder="Search"
        onKeyDown={handleEnterKeyDown}
      />

      <button
        onClick={handleSearchSubmit}
        onMouseDown={(e) => e.preventDefault()}
      >
        <SearchIcon
          className={cx("search-icon")}
        />
      </button>
    </div>
  );
}

export default SearchBox;
