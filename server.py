from flask import Flask, jsonify, request

app = Flask(__name__)

"""
database
[name, title, description, number], ...]
"""
MEDICAL_STORE = []
HOUSING_STORE = []
FOOD_STORE = []
TRANSPORT_STORE = []

@app.route('/get_medical_info', methods=['GET'])
def get_medical_info():
    return ",".join(titles[1] for titles in MEDICAL_STORE)

@app.route('/get_housing_info', methods=['GET'])
def get_housing_info():
    return ",".join(titles[1] for titles in HOUSING_STORE)

@app.route('/get_food_info', methods=['GET'])
def get_food_info():
    return ",".join(titles[1] for titles in FOOD_STORE)

@app.route('/get_transport_info', methods=['GET'])
def get_transport_info():
    return ",".join(titles[1] for titles in TRANSPORT_STORE)

@app.route('/get_full_medical', methods=['GET'])
def get_full_medical():
    position = request.args.get("position")
    print(position)
    return ",".join(titles for titles in MEDICAL_STORE[int(position)])

@app.route('/get_full_housing', methods=['GET'])
def get_full_housing():
    position = request.args.get("position")
    return ",".join(titles for titles in HOUSING_STORE[int(position)])

@app.route('/get_full_food', methods=['GET'])
def get_full_food():
    position = request.args.get("position")
    return ",".join(titles for titles in FOOD_STORE[int(position)])

@app.route('/get_full_transport', methods=['GET'])
def get_full_transport():
    position = request.args.get("position")
    return ",".join(titles for titles in TRANSPORT_STORE[int(position)])

@app.route('/save_medical_info', methods=['PUT'])
def save_medical_info():
    """
    request body contains name, title and description
    """
    if request.is_json:
        data = request.get_json()
        name = data.get('name')
        title = data.get('title')
        description = data.get('description')
        number = data.get('number')

        if name is None or title is None:
            return "Invalid input"

        MEDICAL_STORE.append([name, title, description, number])

        print(MEDICAL_STORE)
        return 'OK'
    else:
        return 'Invalid input'

@app.route('/save_housing_info', methods=['PUT'])
def save_housing_info():
    """
    request body contains name, title and description
    """
    if request.is_json:
        data = request.get_json()
        name = data.get('name')
        title = data.get('title')
        description = data.get('description')
        number = data.get('number')

        if name is None or title is None:
            return "Invalid input"

        HOUSING_STORE.append([name, title, description, number])

        print(HOUSING_STORE)
        return 'OK'
    else:
        return 'Invalid input'

@app.route('/save_food_info', methods=['PUT'])
def save_food_info():
    """
    request body contains name, title and description
    """
    if request.is_json:
        data = request.get_json()
        name = data.get('name')
        title = data.get('title')
        description = data.get('description')
        number = data.get('number')

        if name is None or title is None:
            return "Invalid input"

        FOOD_STORE.append([name, title, description, number])

        print(FOOD_STORE)
        return 'OK'
    else:
        return 'Invalid input'

@app.route('/save_transport_info', methods=['PUT'])
def save_transport_info():
    """
    request body contains name, description
    """
    if request.is_json:
        data = request.get_json()
        name = data.get('name')
        title = data.get('title')
        description = data.get('description')
        number = data.get('number')

        if name is None or title is None:
            return "Invalid input"

        TRANSPORT_STORE.append([name, title, description, number])

        print(TRANSPORT_STORE)
        return 'OK'
    else:
        return 'Invalid input'

@app.route('/delete_info', methods=['PUT'])
def delete_info():
    """
    request body contains name, service and title
    """
    if request.is_json:
        data = request.get_json()
        name = data.get('name')
        title = data.get('title')
        service = data.get('service')

        if name is None or title is None or service is None:
            return "Invalid input"

        if service == "MEDICAL":
            for post in MEDICAL_STORE:
                if post[0] == name and post[1] == title:
                    MEDICAL_STORE.remove(post)
                    print(MEDICAL_STORE)
        elif service == "HOUSING":
            for post in HOUSING_STORE:
                if post[0] == name and post[1] == title:
                    HOUSING_STORE.remove(post)
                    print(HOUSING_STORE)
        elif service == "FOOD":
            for post in FOOD_STORE:
                if post[0] == name and post[1] == title:
                    FOOD_STORE.remove(post)
                    print(FOOD_STORE)
        elif service == "TRANSPORT":
            for post in TRANSPORT_STORE:
                if post[0] == name and post[1] == title:
                    TRANSPORT_STORE.remove(post)
                    print(TRANSPORT_STORE)

        return 'OK'
    else:
        return 'Invalid input'

if __name__ == "__main__":
    pass
