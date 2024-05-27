import { useNavigate } from "react-router-dom";
import Dropdown from "@mui/joy/Dropdown";
import Menu from "@mui/joy/Menu";
import MenuButton from "@mui/joy/MenuButton";
import MenuItem from "@mui/joy/MenuItem";
const DropdownMenu = ({ title, links }) => {
  const navigate = useNavigate();
  return (
    <Dropdown>
      <MenuButton
        size="lg"
        sx={{
          width: "250px",
          backgroundColor: "#173C57",
          color: "white",
          "&:hover": {
            backgroundColor: "#125680",
          },
        }}>
        {title}
      </MenuButton>
      <Menu>
        {links.map((link) => (
          <MenuItem
            onClick={() => {
              navigate(link.url);
            }}
            size="lg"
            sx={{
              width: "250px",
            }}>
            {link.title}
          </MenuItem>
        ))}
      </Menu>
    </Dropdown>
  );
};

export default DropdownMenu;
