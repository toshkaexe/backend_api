package server.restfull.service;

import java.io.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.restfull.repo.UserRepository;
import server.restfull.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {

        dataExtractedFromCVS();
        return userRepository.findAll();
    }


    public List<User> getByEmail(String name) {
        dataExtractedFromCVS();
        return userRepository.getByEmail(name);
    }

    public List<User> getByPhone(String name) {
        dataExtractedFromCVS();
        return userRepository.getByPhone(name);

    }

    public List<User> getBySource(String name) {
        dataExtractedFromCVS();
        return userRepository.getBySource(name);

    }

    public User getById(String id) {
        dataExtractedFromCVS();
        return userRepository.getByID(id);

    }


    public List<Object[]> getNumberUsersBySource() {
        dataExtractedFromCVS();
        return userRepository.numberUserBySource();
    }

    public List<Object[]> getAllSource() {
        dataExtractedFromCVS();
        return userRepository.getAllSourcesDistinct();
    }

    public void addUser(User user) {
        user.setId(String.valueOf(System.currentTimeMillis()));
        userRepository.save(new User(user.getId(), user.getEmail(), user.getPhone(), user.getSource()));
        writeToFile(user);

    }

    private void dataExtractedFromCVS() {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                userRepository.save(new User(data[0], data[1], data[2], data[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void writeToFile(User user) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/data.csv", true));
            out.newLine();
            out.write(user.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateUser(String id, User user) {
        dataExtractedFromCVS();
        User updatedUser = userRepository.getByID(id);
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setSource(user.getSource());
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/data.csv", false));
            List<User> updUser = userRepository.findAll();
            for (User usr : updUser) {
                out.write(usr.toString());
                out.newLine();
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}






/*

	public User getUserById(String id) {

		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			if ((u.getId()).equals(id)) {
				return users.get(i);
			}
		}

		log.error(String.format("Status: 404: We do not find User by id=%s, USER[id=%s]", id, id));
		return new User("--", "--", "--", "--");
	}

	public void deleteUser(String id) {

		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			if (u.getId().equals(id)) {
				users.remove(i);

			}

		}

	}

	public void addUser(User user) {
		users.add(user);
		System.out.println(user.toString());

	}

	public List<User> getAllUsers() {

		return users;
	}

	public void updateUser(String id, User user) {
		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			if (u.getId().equals(id)) {
				users.set(i, user);
				return;
			}

		}

	}

	 */

