from flask import Flask, request, jsonify
import joblib
import pandas as pd

app = Flask(__name__)

# Load the model and the preprocessor
model, preprocessor = joblib.load('../data/random_forest_model.pkl')

@app.route('/predict', methods=['POST'])
def predict():
    # Get data from POST request
    data = request.get_json(force=True)
    
    # Prepare the data for prediction
    input_data = {'PurchaseAmount': data['PurchaseAmount'], 'ReturnCondition': data['ReturnCondition']}
    df_input = pd.DataFrame([input_data])
    input_processed = preprocessor.transform(df_input)

    # Make prediction
    prediction = model.predict(input_processed)
    
    # Send back the result as json
    result = {'prediction': 'Fraud' if prediction[0] else 'Not Fraud'}
    return jsonify(result)

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
