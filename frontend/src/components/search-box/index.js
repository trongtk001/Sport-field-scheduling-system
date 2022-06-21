import { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import classNames from "classnames/bind";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useDispatch } from "react-redux/es/exports";

import styles from "./SearchBox.module.scss";
import { addSearchValue } from "../../redux/redux-actions";
const cx = classNames.bind(styles);

function SearchBox() {
  const [searchValue, setSearchValue] = useState("");

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const inputRef = useRef();

  const handleSearchSubmit = () => {
    if (searchValue) {
      navigate(`/search?q=${searchValue}`);
      dispatch(addSearchValue(searchValue));

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
        <FontAwesomeIcon
          className={cx("search-icon")}
          icon={faMagnifyingGlass}
        />
      </button>
    </div>
  );
}

export default SearchBox;
