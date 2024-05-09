import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, OneHotEncoder
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline

def load_data(filepath):
    return pd.read_csv(filepath)

def preprocess_data(data):
    # Define the features and the target
    X = data[['PurchaseAmount', 'ReturnCondition']]
    y = data['fraud']

    # Define the preprocessing for categorical columns
    categorical_features = ['ReturnCondition']
    categorical_transformer = Pipeline(steps=[
        ('onehot', OneHotEncoder(handle_unknown='ignore'))
    ])

    # Create a column transformer to apply the transformations
    preprocessor = ColumnTransformer(
        transformers=[
            ('cat', categorical_transformer, categorical_features)
        ],
        remainder='passthrough'
    )

    X_processed = preprocessor.fit_transform(X)
    return X_processed, y, preprocessor

def split_data(X, y):
    return train_test_split(X, y, test_size=0.2, random_state=42)
